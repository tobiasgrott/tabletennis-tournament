angular.module('ngapp', ['ngapp.services', 'ngapp.controllers', 'ngRoute', 'ui.bootstrap']).config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
	$routeProvider.when('/views/player-list', { templateUrl: 'views/player-list.html', controller: 'PlayerListCtrl' });
	$routeProvider.when('/views/player-detail/:id', { templateUrl: 'views/player-detail.html', controller: 'PlayerDetailCtrl' });
	$routeProvider.when('/views/player-creation', { templateUrl: 'views/player-creation.html', controller: 'PlayerCreationCtrl' });
	$routeProvider.when('/views/ranking/:type', { templateUrl: 'views/ranking.html', controller: 'RankingCtrl' });
	$routeProvider.when('/views/crosstable', { templateUrl: 'views/crosstable.html', controller: 'CrossTableCtrl' });
	$routeProvider.when('/views/rounds', { templateUrl: 'views/rounds.html', controller: 'RoundCtrl' });
	$routeProvider.when('/views/rounds/:id', { templateUrl: 'views/rounds.html', controller: 'RoundCtrl' });
	$routeProvider.otherwise({ redirectTo: 'views/player-list' });
	$httpProvider.defaults.useXDomain = true; 
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);