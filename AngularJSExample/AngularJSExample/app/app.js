var dashboardApp = angular.module('dashboardApp', [
  'ngRoute',
  'dashboardControllers',
  'agServices'
]);

dashboardApp.config(['$routeProvider',
  function ($routeProvider) {
      $routeProvider.
        when('/dashboard', {
            templateUrl: 'dashboard.html',
            controller: 'DashboardCtrl'
        }).
          when('/dashboard/submitTransaction', {
              templateUrl: 'submit-transaction.html',
              controller: 'SubmitTransactionCtrl'
          }).
        when('/fund/:code', {
            templateUrl: 'fund-detail.html',
            controller: 'FundDetailCtrl'
        }).
        otherwise({
            redirectTo: '/dashboard'
        });
  }]);

var dashboardControllers = angular.module('dashboardControllers', []);

// Define our root-level controller for the application.
dashboardControllers.controller(
    "AppController",
    function ($scope, $route, $routeParams) {

        $scope.isOverview = true;
        $scope.isFundDetail = false;

        // Update the rendering of the page.
        render = function () {

            // Pull the "action" value out of the
            // currently selected route.
            if ($route.current.loadedTemplateUrl != undefined) {
                var template = $route.current.loadedTemplateUrl;

                // Reset the booleans used to set the class
                // for the navigation.
                var isOverview = (template == "dashboard.html");
                var isFundDetail = (template == "fund-detail.html");
                
                $scope.isFundDetail = isFundDetail;
                $scope.isOverview = isOverview;
            }


        };

        // Listen for changes to the Route. When the route
        // changes, let's set the renderAction model value so
        // that it can render in the Strong element.
        $scope.$on(
            "$routeChangeSuccess",
            function ($currentRoute, $previousRoute) {
                // Update the rendering.
                render();

            }
        );

    }
);