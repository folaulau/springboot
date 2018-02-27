(function() {
    'use strict';

    angular
        .module('springboot')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
    	$stateProvider.state('form-upload-download', {
            parent: 'public',
            url: '/form-upload-download',
            views: {
                'main@': {
                    templateUrl: '/form-upload-download/form.html'
                }
            }
        });
    }
})();
