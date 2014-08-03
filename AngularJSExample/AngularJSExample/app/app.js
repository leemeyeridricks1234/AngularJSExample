var dashboardApp = angular.module('dashboardApp', []);

var dashboardApp = angular.module('dashboardApp', [
  'ngRoute',
  'dashboardControllers',
  'agServices'
]);

dashboardApp.config(['$routeProvider',
  function ($routeProvider) {
      $routeProvider.
        when('/dashboard', {
            templateUrl: 'app/dashboard/dashboard.html',
            controller: 'DashboardCtrl'
        }).
          when('/dashboard/submitTransaction', {
              templateUrl: 'app/dashboard/submit-transaction.html',
              controller: 'SubmitTransactionCtrl'
          }).
        when('/fund/:code', {
            templateUrl: 'app/fund/fund-detail.html',
            controller: 'FundDetailCtrl'
        }).
        otherwise({
            redirectTo: '/dashboard'
        });
  }]);

var dashboardControllers = angular.module('dashboardControllers', []);