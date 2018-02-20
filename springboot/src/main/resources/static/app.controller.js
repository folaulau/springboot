(function() {
    'use strict';
    angular.module('springboot').controller('formController', function($scope, $state, $log) {
        $log.log("form controller");
        var form = this;
        // we will store all of our form data in this object
        $scope.formData = {};
        
        $scope.goNext = false;
        
        init();
        
        function init(){
        		$log.log($state.$current.name);
        }
        
        $scope.next = function(){
        		$log.log("next");
        		
        		if($state.$current.name=="form.profile"){
        			$state.go("form.career");
        		}else if($state.$current.name=="form.career"){
        			$state.go("form.reading");
        		}else if($state.$current.name=="form.reading"){
        			$state.go("form.payment");
        		}else if($state.$current.name=="form.payment"){
        			
        		}
        		
        }
        
        $scope.back = function(){
    			$log.log("back");
    			
    			if($state.$current.name=="form.profile"){
        		}else if($state.$current.name=="form.career"){
        			$state.go("form.profile");
        		}else if($state.$current.name=="form.reading"){
        			$state.go("form.career");
        		}else if($state.$current.name=="form.payment"){
        			$state.go("form.reading");
        		}
        }
        // function to process the form
        $scope.processForm = function() {
            alert('awesome!');  
        };
        
    });
    
    angular.module('springboot').controller('profileController', function($scope, $log) {
    		$log.log("profile controller");
 
        
    });
    
    angular.module('springboot').controller('interestController', function($scope, $log) {
		$log.log("interest controller");

    
    });
    
    angular.module('springboot').controller('paymentController', function($scope, $log) {
		$log.log("payment controller");

    
    });
    
})();