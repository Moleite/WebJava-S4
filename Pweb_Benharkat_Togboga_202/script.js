// Global variables
// ================


var fb = new Firebase("https://travelguide.firebaseio.com/"),
						// Firebase connection
	connected = false,	// Indicates user connection state
	map,				// Map container once user is logged in
	bar,				// Loading bar class container
	account,			// User email
	selectedCities,		// Selected cities' list...
	selectedCoords,		// ...and their coordinates
	selectedCity,		// Last selected city...
	selectedPos,		// ...and its coordinates
	currentLine,		// PolyLine representing the Journey container
	bg,					// Background map container once user is logged out
	bglng = 0,			// Current longitude of background map
	loopid,				// ID of the loop animation
	userCities,			// All of the user's cities
	startMarker,		// First city of the Journey
	endMarker,			// Last city of the Journey
	autocomplete,		// Container for Google's autocomplete
	cities = [			// Preloaded cities
		"Paris"
		
	];

// General purpose functions
// =========================

// These functions simplify AJAX calls

function buildCityURL(city) {
	return "http://nominatim.openstreetmap.org/search?&limit=1&format=json&addressdetails=0&q=" + city;
}

function buildPositionURL(l) {
	return "http://nominatim.openstreetmap.org/reverse?format=json&addressdetails=1&lat=" + l.lat + "&lon=" + l.lng;
}

function buildImagesURL(city) {
	return "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&q=" + city;
}

function buildWikipediaURL(subject) {
	return "http://en.wikipedia.org/w/api.php?format=json&action=opensearch&search=" + subject;
}

function buildWeatherURL(l) {
	return "http://api.openweathermap.org/data/2.5/weather?lat=" + l.lat + "&lon=" + l.lng + "&units=metric";	
}

// Main code
// =========

$(function() {
	$("html").removeClass("loading");

	function disconnect() {
		connected = false;
		disconnectedScreen();
	}
	
	//vue utilisateur déconnecté
	function disconnectedScreen() {
		$("#connected").hide();
		$("#disconnected").show();
	
		$("#menu").tabs();
		
		// Connection
		$("#connect-form").submit(function(e) {
			e.preventDefault();
		
			fb.authWithPassword({
				email : $("#connect-form input[type='email']").val(),
				password : $("#connect-form input[type='password']").val()
			}, function authHandler(error, authData) {
				console.log(error, authData);
				
				if (error === undefined) {
					connect(authData);
				} else {
					alert(error.message);
				}
			});
		});
		
		// Inscription
		$("#register-form").submit(function(e) {
			e.preventDefault();
			
			fb.createUser({
				email : $("#register-form input[type='email']").val(),
				password : $("#register-form input[type='password']").val()
			}, function(error, userData) {
				console.log(error);

				if (error) {
					alert(error.message);
				} else {
					alert("Vous avez bien été enregistrer");
				}
			});
		});
	
		// The following code animates the background map

		function bgLoop() {
			bg.setView({lat: 20, lng: (bglng += 0.1)}, 3);
		 
			if(connected === false) {
				loopid = requestAnimationFrame(bgLoop);
			} else {
				cancelAnimationFrame(loopid);
				loopid = undefined;
			}
		}
	
		if(bg === undefined) {
			bg = L.map('bg', {
				zoomControl: false,
				attributionControl: false
			}).setView([20, 0], 3);

			L.tileLayer('http://{s}.tile.stamen.com/toner/{z}/{x}/{y}.png', {
				minZoom: 3,
				maxZoom: 3
			}).addTo(bg);
		}
		
		if(loopid == undefined) {
			bgLoop();
		}
	}

	function connect(auth) {
		connected = true;
		connectedScreen(auth);
	}

	// vus utilisateur connecté
	function connectedScreen(auth) {
			function Loading() {
				var that = this;

				this.i = 0;
				this.n = 4;
			}

			Loading.prototype.start = function() {
				$("#connected").addClass("loading");
				$("#bar").width("0%");
			};

			Loading.prototype.add = function() {
				this.i++;

				$("#bar").width((Math.floor(this.i * 100 / this.n)) + "%");

				if(this.i === this.n) {
					$("#connected").removeClass("loading");

					this.i = 0;
				}
			};

			if(bar === undefined) {
				bar = new Loading();
			}
		
		// Initialization
			$("#disconnected").hide();
			$("#connected").show();
			
			// Some variables initialisation
			selectedCity = null;
			selectedPos = null;
			
			selectedCities = [];
			selectedCoords = [];
			
			currentLine = null;

			// enregistrement du compte de l'utilisateur
			account = auth.uid;
			$("#account").text(auth.password.email);

			$("#infos").tabs();
			
			// La carte
			if(map === undefined) {
				map = L.map('map').setView([51.505, -0.09], 5);

				L.tileLayer('http://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png', {
					minZoom: 2,
					maxZoom: 10,
					noWrap: true
				}).addTo(map);

				map.setMaxBounds([[85, -180], [-85, 180]]);
			}

			// ajout des villes a la liste
			userCities = cities;
			reloadCities();
			
			$("#map").droppable({
				accept: "#usercities li",
				over: function(event, ui) {
					$("#map").addClass("drop");
				},
				out: function(event, ui) {
					$("#map").removeClass("drop");
				},
				drop: function(event, ui) {
					bar.start();
					displayInfos(ui.draggable.children("span:first-child").text());
				}
			});
		
			// Initializing autocompletion
			if(autocomplete === undefined) {			
				autocomplete = new google.maps.places.Autocomplete(
					document.querySelector("#search input"), {
						types: ['(cities)']
					}
				);
			}

		// fontions générales
			function displayInfos(city) {
				selectedCity = city;

				getPos(city);
			}
			
			function displayInfosOfSelectedCity() {
				$("#city .toolbar").show();
				
				$("#infos").removeClass("start");
				$("#map").removeClass("drop");
				
				$("#city").scrollTop(0);
				$("#city h2").text(selectedCity);
					
				centerOn(selectedPos);
				showInfosAbout(selectedCity);
				showImagesFrom(selectedCity);
				showWeatherOf(selectedPos);

				$("#infos").tabs("option", "active", 1);
			}
			
			// La liste des villes
			function reloadCities() {
				$("#usercities").empty();
				
				for(var i = 0; i < userCities.length; i++) {
					$("#usercities").append("<li><span>" + userCities[i] + "</span><span>&times;</span></li>");
				}
			
				$("#usercities li").draggable({
					revert: true,
					stack: "#usercities li"
				});

				$("#usercities li span:last-child").click(function(e) {
					var city = $(this).parent().children("span:first-child").text();
					var index = userCities.indexOf(city);
					
					if (index > -1) {
						userCities.splice(index, 1);
					}
					
					reloadCities();
				})
			}

			// La position de la ville
			function getPos(city) {
				$.ajax({
					url: buildCityURL(city),
					dataType: 'json',
					success: function(data) {
						selectedPos = L.latLng(data[0].lat, data[0].lon);
						bar.add();
						displayInfosOfSelectedCity();
					}
				});
			}
			
			// Recentrage sur la ville choisie
			function centerOn(coords) {
				map.setView(coords, 10);
			}

			function showcase(url) {
				$("#showcase img").attr("src", "");
				$("#showcase img").attr("src", url);
				$("#showcase").show();
			}

		

			
			// Adding a city to the list
			function addCityToList() {
				if(connected === true && $("#search input").val() !== "") {
					var location	= autocomplete.getPlace();
					
					if (location.address_components) {
						var city		= location.address_components[0].long_name;
						var country		= location.address_components[3].long_name;
						
						userCities.unshift(city + ", " + country);
						$("#search input").val("");
						reloadCities();
					}
				}
			}

			function showImagesFrom(city) {
				$("#images").empty();
				
				$.ajax({
					type: "GET",
					url: buildImagesURL(city),
					dataType: "jsonp",
					cache: false,
					crossDomain: true,
					processData: true,
					success: function (data) {				
						var images = data.responseData.results;
						
						for(var i = 0; i < images.length; i++) {
							$("#images").append(
								"<img src=" + images[i].tbUrl + " data-high='" + images[i].unescapedUrl +
								"' height=" + images[i].tbHeight + " width=" + images[i].tbWidth + ">");
						}
							
						$("#images img").click(function() {
							showcase($(this).attr("data-high"));
						}).error(function(){
							$(this).hide();
						});

						bar.add();
					}
				});
			}
		
			// Displaying a city's description via Wikipedia
			function showInfosAbout(city) {
				$("#city p").empty();
				
				$.ajax({
					type: "GET",
					url: buildWikipediaURL(city),
					dataType: "jsonp",
					cache: false,
					crossDomain: true,
					processData: true,
					success: function (data) {
						$("#city p").html("<span></span>" + data[2][0]);
						bar.add();
					}
				});
			}

			// Shows the weather of a given city via the OpenWeather API
			function showWeatherOf(latlng) {
				$("#weather").empty();
				
				$.ajax({
					type: "GET",
					url: buildWeatherURL(latlng),
					dataType: "jsonp",
					cache: false,
					crossDomain: true,
					processData: true,
					success: function (data) {
						var temp = Math.floor(parseInt(data.main.temp, 10));
						$("#weather").html("<span>" + temp + "°</span><span>" + data.weather[0].main + "</span>");
						bar.add();
					}
				});
			}
		
		// Events
			$("#showcase").click(function() {
				$(this).hide();
			});
			
			$("#search button").click(function(e) {
				e.preventDefault();
				addCityToList();
			});

			// Geolocalisation of user
			$("#geolocate").click(function() {
				if ("geolocation" in navigator) {
					navigator.geolocation.getCurrentPosition(function(position) {
						selectedPos = L.latLng(position.coords.latitude, position.coords.longitude);
						bar.start();
						
						$.ajax({
							url: buildPositionURL(selectedPos),
							dataType: 'json',
							success: function(data) {
								selectedCity = data.address.city;
								bar.add();
								displayInfosOfSelectedCity();
							}
						});
						
						bar.add();
					});
				} else {
					alert("Geolocalisation is not available on your computer");
				}
			});

			// centrage sur la ville choisie
			$("#locate").click(function() {
				centerOn(selectedPos);
			})



			// Affichage de l'aide
			$("#gethelp").click(function() {
				$("#help").show();
			});

			$("#help").click(function() {
				$("#help").hide();
			});

			$("#help > div").click(function(e) {
				e.stopPropagation();
			});
			
			// Disconnection
			$("#logout").click(function() {
				fb.unauth();
				connected = false;
				disconnectedScreen();
			});
	}

	// Initial detection of user authentification
	fb.onAuth(function(authData) {
		if (authData) {
			connect(authData);
		} else {
			disconnect();
		}
	});
});