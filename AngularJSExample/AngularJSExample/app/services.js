var agServices = angular.module('agServices', ['ngResource']);

agServices.factory('FundListService', ['$resource',
  function ($resource) {
      return $resource('http://localhost:8081/api/funds', {}, {
          query: { method: 'GET', isArray: true }
      });
      //data/funds.json
      //http://localhost:8081/api/funds
  }]);

agServices.factory('FundDetailService', ['$resource',
  function ($resource) {
      return $resource('http://localhost:8081/api/funds/:code', {}, {
          query: { method: 'GET', params: { code: 'code' }, isArray: true }
      });
  }]);


agServices.factory('Instruction', ['$resource',
  function ($resource) {
      return $resource("http://localhost:8081/api/instructions");
  }]);

agServices.factory('ElevationService', ['$resource',
  function ($resource) {
      return $resource("http://localhost:8081/api/ElevationService/42/80");
  }]);
