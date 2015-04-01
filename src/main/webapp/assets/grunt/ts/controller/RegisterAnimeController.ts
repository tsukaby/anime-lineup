///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module RegisterAnimeControllerModule {
    "use strict";

    export interface IScope extends ng.IScope {
        year:number;
        season:string;
        alerts:Array<any>;
        closeAlert:(number) => void;
    }

    export class RegisterAnimeController {
        constructor($scope:IScope, $routeParams:any) {
            $scope.year = Number($routeParams.year);
            $scope.season = $routeParams.season;
            if ($routeParams.success !== undefined) {
                $scope.alerts = [
                    { type: "success", msg: "登録しました。" }
                ];
            }

            /**
             * メッセージを消す。
             *
             * @param index
             */
            $scope.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
        }
    }
}
