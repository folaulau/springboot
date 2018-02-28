(function() {
    'use strict';

    angular
        .module('client')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
    	$stateProvider.state('home', {
            parent: 'app',
            url: '/',
            views: {
                'main@': {
                		templateUrl: '/app/home/home.html'
                }
            }
        });
    }
})();
