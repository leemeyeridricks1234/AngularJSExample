dashboardControllers.controller('DashboardCtrl', ['$scope', '$http', 'FundListService',
  function ($scope, $http, fundListService) {
      $scope.funds = fundListService.query();
      $scope.orderProp = 'percentageMovement';
  }]);