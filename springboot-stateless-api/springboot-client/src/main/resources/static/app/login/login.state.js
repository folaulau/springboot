(function() {
    'use strict';

    angular
        .module('client')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
    	$stateProvider.state('login', {
            parent: 'app',
            url: '/login',
            views: {
                'main@': {
                		templateUrl: '/app/login/login.html'
                }
            }
        });
    }
})();
