(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('home', {
            parent: 'public',
            url: '/',
            views: {
                'main@': {
                    templateUrl: '/home/home.html'
                }
            }
        });
    }
})();
