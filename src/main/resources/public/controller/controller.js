var app = angular.module('ngapp.controllers', []);

// PlayerListController
app.controller("PlayerListCtrl", ['$scope', 'PlayersFactory', 'PlayerFactory', '$location',
    function ($scope, PlayersFactory, PlayerFactory, $location) {
        // callback for ng-click 'editPlayer':
        $scope.editPlayer = function (playerId) {
            $location.path('/views/player-detail/' + playerId);
        };

        // callback for ng-click 'deletePlayer':
        $scope.deletePlayer = function (playerId) {
            PlayerFactory.delete({ id: playerId });
            $scope.players = PlayersFactory.query();
        };

        //callback for ng-click 'createPlayer':
        $scope.createNewPlayer = function () {
            $location.path('/views/player-creation');
        };

        $scope.players = PlayersFactory.query();
    }
]);

// PlayerDetailController
app.controller("PlayerDetailCtrl", ['$scope', '$routeParams', 'PlayerFactory', '$location',
    function ($scope, $routeParams, PlayerFactory, $location) {
        // callback for ng-click 'updatePlayer':
        $scope.updatePlayer = function () {
            PlayerFactory.update($scope.player);
            $location.path("/views/player-list");
        };

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path("/views/player-list");
        };

        $scope.player = PlayerFactory.show({ id: $routeParams.id });
    }
]);

//PlayerCreationController
app.controller("PlayerCreationCtrl", ['$scope', 'PlayersFactory', '$location',
    function ($scope, PlayersFactory, $location) {
        // callback for ng-click 'createNewPlayer':
        $scope.createNewPlayer = function () {
            PlayersFactory.create($scope.player);
            $scope.players = PlayersFactory.query();
            $location.path('/views/player-list');
        };
    }
]);

// RankingController
app.controller("RankingCtrl", ['$scope', '$routeParams', 'RankingFactory', '$location',
    function ($scope, $routeParams, RankingFactory, $location) {
        $scope.ranking = RankingFactory.query({ type: $routeParams.type });
    }
]);

// Crosstable controller
app.controller("CrossTableCtrl", ['$scope', 'RankingFactory',
    function ($scope, RankingFactory) {
        $scope.crosstable = RankingFactory.query({ type: 'crosstable' });
    }
]);

// RoundController
app.controller("RoundCtrl", ['$scope', 'RoundsFactory', 'RoundFactory', 'GameFactory', 'RoundCheckFactory', '$location', '$routeParams',
    function ($scope, RoundsFactory, RoundFactory, GameFactory, RoundCheckFactory, $location, $routeParams) {
        // callback for generation of new round
        $scope.createNewRound = function () {
            $scope.round = RoundFactory.create();
            $location.path('/views/rounds');
        };
        // callback for saving Game
        $scope.saveGame = function (game, gameId, sets1, sets2) {
            GameFactory.setResult({ id: gameId }, { id: gameId, sets1: sets1, sets2: sets2 });
            game.sets1 = sets1;
            game.sets2 = sets2;
            $scope.newRound = RoundCheckFactory.checkNewRound();
        };
        // get list of rounds
        $scope.roundNrs = function () {
            var range = [];
            for (var i = 1; i <= $scope.newRound.maxRound; i++) {
                range.push(i);
            }
            return range;
        }

        if ($routeParams.id != null) {
            $scope.round = RoundsFactory.show({ id: $routeParams.id });
        } else {
            $scope.round = RoundFactory.getCurrent();
        }
        $scope.newRound = RoundCheckFactory.checkNewRound();
    }
]);