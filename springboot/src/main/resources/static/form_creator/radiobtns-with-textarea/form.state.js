(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-radio-btn-with-textarea', {
            parent: 'public',
            url: '/radio-with-textarea',
            views: {
                'main@': {
                    templateUrl: '/form_creator/radiobtns-with-textarea/form.html'
                }
            }
        });
    }
})();
