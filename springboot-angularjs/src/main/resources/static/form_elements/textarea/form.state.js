(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-textarea', {
            parent: 'public',
            url: '/textarea',
            views: {
                'main@': {
                    templateUrl: '/form_elements/textarea/form.html'
                }
            }
        });
    }
})();
