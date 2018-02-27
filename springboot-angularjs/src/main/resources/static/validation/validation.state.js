(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('validation', {
            parent: 'public',
            url: '/validation',
            views: {
                'main@': {
                    templateUrl: '/validation/validation.html'
                }
            }
        });
    }
})();
