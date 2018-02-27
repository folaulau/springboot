(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-checkbox', {
            parent: 'public',
            url: '/checkbox',
            views: {
                'main@': {
                    templateUrl: '/form_elements/checkboxes/form.html'
                }
            }
        });
    }
})();
