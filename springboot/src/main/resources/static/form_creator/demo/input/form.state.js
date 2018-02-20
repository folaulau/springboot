(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
//    		$stateProvider.state('form-demo.input', {
//    			url: "",
//    			templateUrl: '/form_creator/demo/input/form.html'
//        });
    }
})();
