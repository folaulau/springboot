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
			url: "/input",
			views: {
                'main@': {
                		templateUrl: '/form_creator/demo/input/form.html'
                }
            }
			
        });
    		$urlRouterProvider.otherwise('/demo/input');
    }
})();
