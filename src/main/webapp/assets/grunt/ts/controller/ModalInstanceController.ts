///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module ModalInstanceControllerModule {
    "use strict";

    export interface IScope extends ng.IScope {
        anime:Model.Anime;
        close:() => void;
    }

    export class ModalInstanceController {
        constructor($scope:IScope, $modalInstance:any, public anime:Model.Anime) {
            $scope.anime = anime;

            $scope.close = () => {
                $modalInstance.close();
            };
        }
    }
}
