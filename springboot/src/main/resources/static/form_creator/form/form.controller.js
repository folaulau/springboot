(function() {
	'use strict';

	angular.module('springboot').controller('FormController', FormController);

	FormController.$inject = [ '$window', '$rootScope', '$scope', '$http', '$uibModal', '$log', '$state', 'FormService'];

	function FormController($window, $rootScope, $scope, $http, $uibModal, $log, $state, FormService) {
		$log.log("form controller");
		var form = this;
		form.data = {};
		form.data.fields = [];
		form.transient = {};
		form.transient.counts = [];
		
		init();
		
		function init(){
			prefill();
			
			form.transient.counts.push(1);
		}
		
		function prefill(){
			form.data.fieldName = "test name";
			//form.data.fields.push({question: "What is your name?", type: "input"});
		}
		
		$rootScope.$on('addField', function(event, args) {
			$log.log("add field into form");
			$log.log(args);
			let field = angular.fromJson(args);
			form.data.fields.push(field);
		});
		
		form.addValue = function(){
			$log.log("add value");
			form.transient.counts.push(form.transient.counts.length);
			
		}
		
		form.edit = function(index){
			$log.log("edit form with index #"+index);
			let field = form.data.fields[index];
			$log.log(field);
		}
		
		form.takeOut = function(index){
			$log.log("take out "+index);
			form.data.fields.splice(index,1);
		}
		
		form.moveUp = function(index){
			$log.log("move up "+index);
			//form.data.fields.splice(index,1);
			var temp = form.data.fields[index];
			form.data.fields[index] = form.data.fields[index-1];
			form.data.fields[index-1] = temp;
		}
		
		form.moveDown = function(index){
			$log.log("move down "+index);
			//form.data.fields.splice(index,1);
			var temp = form.data.fields[index];
			form.data.fields[index] = form.data.fields[index+1];
			form.data.fields[index+1] = temp;
		}
		
		form.submit = function(){
			FormService.add(form.data).then(function(data){
				$log.log("good");
				$log.log(data);
			}).catch(function(error){
				$log.log("error");
				$log.log(error);
			});
		}
		
		
	}
})();
