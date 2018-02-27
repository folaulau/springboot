(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-input', {
            parent: 'public',
            url: '/input',
            views: {
                'main@': {
                    templateUrl: '/form_elements/input/form.html'
                }
            }
        });
    }
})();
