dashboardControllers.controller('SubmitTransactionCtrl', ['$scope', '$routeParams', 'FundDetailService',
  function ($scope, $routeParams, fundDetailService) {
      $scope.code = $routeParams.code;

      //can also use get
      $scope.tempList = fundDetailService.query({ code: $routeParams.code }, function (funds) {
          //$scope.tempList = funds;
      });
  }]);