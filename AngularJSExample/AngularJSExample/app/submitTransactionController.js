dashboardControllers.controller('SubmitTransactionCtrl', ['$scope', '$routeParams', 'FundListService',
  function ($scope, $routeParams, fundListService) {
      var instructionType = "Additional Contribution";
      //$scope.code = $routeParams.code;

      $scope.funds = fundListService.query(function (funds) {
          $scope.reset();
      });

      $scope.instruction = {};
      
      $scope.accounts = [
          { number: 'AGUT3432432', name: 'dark' },
          { number: 'AGRA3432423', name: 'light' }
      ];
      
      $scope.reset = function () {
          $scope.instruction.account = $scope.accounts[0];
          $scope.instruction.fund = $scope.funds[0];
      };

      $scope.submit = function (instruction) {
          alert(instruction.fund);
      };

      
  }]);