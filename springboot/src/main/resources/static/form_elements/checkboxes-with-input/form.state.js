(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-checkbox-with-input', {
            parent: 'public',
            url: '/checkbox-with-input',
            views: {
                'main@': {
                    templateUrl: '/form_elements/checkboxes-with-input/form.html'
                }
            }
        });
    }
})();
