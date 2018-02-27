(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
    		$stateProvider.state('form-demo', {
            parent: 'public',
            url: '/demo',
            views: {
                'main@': {
                    templateUrl: '/form_creator/demo/form.html'
                }
            }
        }).state('form-demo.input', {
			url: "/",
			templateUrl: '/form_creator/demo/input/form.html'
        }).state('form-demo.textarea', {
			url: "/",
			templateUrl: '/form_creator/demo/textarea/form.html'
			
        }).state('form-demo.checkbox', {
			url: "/",
			templateUrl: '/form_creator/demo/checkboxes/form.html'
			
        }).state('form-demo.radiobtn', {
			url: "/",
			templateUrl: '/form_creator/demo/radiobtns/form.html'
			
        });
    		$urlRouterProvider.otherwise('/demo/');
    }
})();
