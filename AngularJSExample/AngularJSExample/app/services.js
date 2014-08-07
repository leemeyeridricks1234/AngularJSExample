var agServices = angular.module('agServices', ['ngResource']);

agServices.factory('FundListService', ['$resource',
  function ($resource) {
      return $resource('data/funds.json', {}, {
          query: { method: 'GET', isArray: true }
      });
      //data/funds.json
      //http://localhost:8081/api/funds
  }]);

agServices.factory('FundDetailService', ['$resource',
  function ($resource) {
      return $resource('data/funds/:code.json', {}, {
          query: { method: 'GET', params: { code: 'code' }, isArray: true }
      });
  }]);


agServices.factory('InstructionService', ['$resource',
  function ($resource) {
      return $resource('data/instructions/', {}, {
          create: { method: 'POST', params: { code: 'code' } }
      });
  }]);