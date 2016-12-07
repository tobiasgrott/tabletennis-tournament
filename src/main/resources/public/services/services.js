var services = angular.module('ngapp.services', ['ngResource']);

var baseUrl = "";

services.factory('PlayersFactory', function ($resource) {
    return $resource(baseUrl + '/players', {},
        {
            query: { method: 'GET', isArray: true },
            create: { method: 'POST' }
        });
});

services.factory('PlayerFactory', function ($resource) {
    return $resource(baseUrl + '/players:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: { id: '@id' } },
        delete: { method: 'DELETE', params: { id: '@id' } }
    });
});

services.factory('RankingFactory', function ($resource) {
    return $resource(baseUrl + '/rankings/:type', {}, {
        query: { method: 'GET', params: { type: '@type' }, isArray: true }
    });
});

services.factory('RoundFactory', function ($resource) {
    return $resource(baseUrl + '/rounds', {}, {
        create: { method: 'POST' },
        getCurrent: { method: 'GET' }
    });
});

services.factory('RoundsFactory', function ($resource) {
    return $resource(baseUrl + '/rounds/:id', {}, {
        show: { method: 'GET', params: { id: '@id' } }
    });
});

services.factory('GameFactory', function ($resource) {
    return $resource(baseUrl + '/games/:id', {}, {
        setResult: { method: 'PUT', params: { id: '@id', sets1: '@sets1', sets2: '@sets2' } }
    });
});

services.factory('RoundCheckFactory', function ($resource) {
    return $resource(baseUrl + '/rounds/checkNewRound', {}, {
        checkNewRound: { method: 'GET' }
    })
});