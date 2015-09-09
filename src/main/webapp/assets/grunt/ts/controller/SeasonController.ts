///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module SeasonControllerModule {
    "use strict";

    export interface IScope extends ng.IScope {
        seasons:Array<Model.Season>;
    }

    export class SeasonController {
        constructor($scope:IScope, seasonService:Service.SeasonService) {

            $scope.seasons = seasonService.getSeasons();
        }
    }
}
