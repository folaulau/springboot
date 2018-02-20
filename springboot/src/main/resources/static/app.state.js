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
			templateUrl : 'profile/view.html'
		})

		// url will be /form/interests
		.state('form.career', {
			url : '/',
			templateUrl : 'career/view.html'
		})

		// url will be /form/payment
		.state('form.payment', {
			url : '/',
			templateUrl : 'payment/view.html'
		})
		
		// url will be /form/payment
		.state('form.reading', {
			url : '/',
			templateUrl : 'reading/view.html'
		});

		// catch all route
		// send users to the form page
		$urlRouterProvider.otherwise('/form/');
	}
})();