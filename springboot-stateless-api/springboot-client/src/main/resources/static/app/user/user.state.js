(function() {
    'use strict';

    angular
        .module('client')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
    	$stateProvider.state('user', {
            parent: 'app',
            url: '/user',
            views: {
                'main@': {
                		templateUrl: '/app/user/user.html'
                }
            }
        });
    }
})();
