(function() {
    'use strict';

    angular
        .module('client')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
    	$stateProvider.state('logout', {
            parent: 'app',
            url: '/logout',
            views: {
                'main@': {
                		templateUrl: '/app/logout/logout.html'
                }
            }
        });
    }
})();
