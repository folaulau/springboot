(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
    	$stateProvider
    	.state('public', {
            abstract: true,
            views: {
            		'form-content@': {
                    templateUrl: '/form_creator/form/form.html'
                }
            }
        })
    	
    	$urlRouterProvider.otherwise('/');
    }
})();