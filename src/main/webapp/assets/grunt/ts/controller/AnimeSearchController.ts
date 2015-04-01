///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module AnimeSearchControllerModule {
    "use strict";

    export interface IScope extends ng.IScope {
        searchByTitle:(string) => void;
        blurSearchBox:() => void;
        logout:() => void;
        isActive:(string) => boolean;
    }

    export class AnimeSearchController {
        constructor($scope:IScope,
                    $rootScope:any,
                    AnimeSearchService:any,
                    NavigationService:any,
                    AuthService:Service.AuthService,
                    $location:ng.ILocationService) {
            /**
             * タイトルによるアニメ検索。
             *
             * @param title 検索するタイトル
             */
            $scope.searchByTitle = (title:string)=> {
                if (!title) {
                    AnimeSearchService.searchByDefault();
                    NavigationService.seasonMode();
                    return;
                }

                AnimeSearchService.searchByTitle(title);
                NavigationService.searchMode(title);
            };

            /**
             * #search_boxのフォーカスを外す。Enterが押されたときなどにフォーカスを外すためのもの
             */
            $scope.blurSearchBox = ()=> {
                angular.element("#search_box").blur();
            };

            /**
             * ログアウトする。
             */
            $scope.logout = ():void => {
                for (var i = 0; i < $rootScope.animes.length; i++) {
                    delete $rootScope.animes[i].status;
                }

                AuthService.logout();
            };

            /**
             * 引数で指定したパスと現在のパスが同じかを判定する。
             *
             * @param route パス
             * @returns {boolean} trueの場合、同じパス
             */
            $scope.isActive = (route:string) => {
                return route === $location.path();
            };
        }
    }
}
