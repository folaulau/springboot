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
                    templateUrl: '/form_creator/checkboxes/form.html'
                }
            }
        });
    }
})();
