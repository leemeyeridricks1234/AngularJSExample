var dashboardApp = angular.module('dashboardApp', [
    'highcharts-ng',
  'ngRoute',
  'dashboardControllers',
  'agServices'
]);

dashboardApp.config(['$routeProvider',
  function ($routeProvider) {
      $routeProvider.
        when('/overview', {
            templateUrl: 'dashboard.html',
            controller: 'DashboardCtrl',
            defaultSubView: 'investmentSummary'
        }).
          when('/overview/profile', {
              templateUrl: 'manage-profile.html',
              controller: 'ManageProfileCtrl'
          }).
          when('/submitTransaction', {
              templateUrl: 'submit-transaction.html',
              controller: 'SubmitTransactionCtrl'
          }).
        when('/funds/:code', {
            templateUrl: 'fund-detail.html',
            controller: 'FundDetailCtrl'
        }).
          when('/funds', {
              templateUrl: 'fund-detail.html',
              controller: 'FundDetailCtrl'
          }).
          when('/chart', {
              templateUrl: 'chart.html',
              controller: 'ChartCtrl'
          }).
        otherwise({
            redirectTo: '/overview'
        });
  }]);

var dashboardControllers = angular.module('dashboardControllers', []);

// Define our root-level controller for the application.
dashboardControllers.controller(
    "AppController",
    function ($scope, $route, $routeParams) {

        $scope.isOverview = true;
        $scope.isFundDetail = false;
        $scope.isSubmitTransaction = false;

        $scope.mainView = "overview";
        $scope.subView = "profile";

        //originalPath

        // Update the rendering of the page.
        render = function () {

            // Pull the "action" value out of the
            // currently selected route.
            if ($route.current.loadedTemplateUrl != undefined) {
                var path = $route.current.originalPath;

                var split = path.split('/');
                if (split.length > 2) {
                    $scope.mainView = split[1];
                    $scope.subView = split[2];
                }
                else {
                    $scope.mainView = split[1];
                    $scope.subView = $route.current.defaultSubView;
                }

                var template = $route.current.loadedTemplateUrl;

                // Reset the booleans used to set the class
                // for the navigation.
                $scope.isOverview = (template == "dashboard.html");
                $scope.isFundDetail = (template == "fund-detail.html");
                $scope.isSubmitTransaction = (template == "submit-transaction.html");
                
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