dashboardControllers.controller('ElevationCtrl', ['$scope', '$routeParams', '$http', 'ElevationService',
  function ($scope, $routeParams, $http, elevationService) {
      $scope.elevation = {
          longitude: 42,
          latitude: 80
      };
      $scope.elevationResult = "";
     
      $scope.submit = function (elevation) {
          var url = "http://localhost:8081/api/ElevationService/" + $scope.elevation.longitude + "/" + $scope.elevation.latitude;
          $http({ method: 'GET', url: url }).
                success(function (data, status, headers, config) {
                    $scope.elevationResult = data;
                }).
                error(function (data, status, headers, config) {
                    alert('error occurred');
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });

      };
      
  }]);