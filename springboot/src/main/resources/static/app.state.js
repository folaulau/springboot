(function() {
	'use strict';

	angular.module('springboot').config(stateConfig);

	stateConfig.$inject = [ '$stateProvider', '$urlRouterProvider' ];

	function stateConfig($stateProvider, $urlRouterProvider) {
		$stateProvider

		// route to show our basic form (/form)
		.state('form', {
			url : '/form',
			views: {
		    		'main@': { 
		    			templateUrl : 'form.html'
		        }
            }
		})

		// nested states
		// each of these sections will have their own view
		// url will be nested (/form/profile)
		.state('form.profile', {
			url : '/',
			templateUrl : 'profile/profile.html'
		})

		// url will be /form/interests
		.state('form.interests', {
			url : '/',
			templateUrl : 'form-interests.html'
		})

		// url will be /form/payment
		.state('form.payment', {
			url : '/',
			templateUrl : 'form-payment.html'
		});

		// catch all route
		// send users to the form page
		$urlRouterProvider.otherwise('/form/');
	}
})();