(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-radio-btn', {
            parent: 'public',
            url: '/radio',
            views: {
                'main@': {
                    templateUrl: '/form_creator/radiobtns/form.html'
                }
            }
        });
    }
})();
