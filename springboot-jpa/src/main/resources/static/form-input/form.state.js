(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-input', {
            parent: 'public',
            url: '/form-input',
            views: {
                'main@': {
                    templateUrl: '/form-input/form.html'
                }
            }
        });
    }
})();
