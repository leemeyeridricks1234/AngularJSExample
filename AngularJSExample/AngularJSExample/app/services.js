var agServices = angular.module('agServices', ['ngResource']);

agServices.factory('FundListService', ['$resource',
  function ($resource) {
      return $resource('data/funds.json', {}, {
          query: { method: 'GET', isArray: true }
      });
  }]);

agServices.factory('FundDetailService', ['$resource',
  function ($resource) {
      return $resource('data/funds/:code.json', {}, {
          query: { method: 'GET', params: { code: 'code' }, isArray: true }
      });
  }]);