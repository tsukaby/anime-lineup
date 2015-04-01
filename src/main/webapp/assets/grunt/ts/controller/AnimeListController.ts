///<reference path="../typings/angularjs/angular.d.ts" />
///<reference path="../typings/angularjs/angular-resource.d.ts" />

///<reference path="../Model.ts" />

module AnimeListControllerModule {
    "use strict";

    export interface IScope extends ng.IScope {
        animes:Array<Model.Anime>;
        currentUser:any;
        isVisibleSearchBox:boolean;
        isLoaded:boolean;
        navigationService:any;
        previousSeason:string;
        nextSeason:string;
        desc:boolean;
        sortTitle:() => void;
        sortViewingHistories:() => void;
        resetScroll:() => void;
        open:(anime:any) => void;
        view:(anime:any, mode:any) => void;
    }

    export class AnimeListController {
        constructor($scope:IScope,
                    $rootScope:any,
                    $http:ng.IHttpService,
                    $stateParams:any,
                    SeasonService:any,
                    $filter:ng.IFilterService,
                    $modal:any,
                    AnimeSearchService:any,
                    NavigationService:any,
                    scroller:any
        ) {
            $scope.isVisibleSearchBox = true;

            // Set current season or specified season
            if (angular.isDefined($stateParams.year) && angular.isDefined($stateParams.seasonType)) {
                $rootScope.season.year = Number($stateParams.year);
                $rootScope.season.seasonType = Number($stateParams.seasonType);
            } else {
                // Set current season if query is not provided or incomplete.
                var current = SeasonService.currentSeason();
                $rootScope.season.year = current.year;
                $rootScope.season.seasonType = current.seasonType;
            }

            //isLoadedによってローディング画像の表示を切り替える
            $scope.isLoaded = false;
            //AnimeSearchService.searchBySeason($rootScope.season.year, $rootScope.season.seasonType, function () {
            AnimeSearchService.searchBySeason($rootScope.season.year, $rootScope.season.seasonType, () => {
                $scope.isLoaded = true;
            });

            //現在のシーズンを設定
            $scope.navigationService = NavigationService;
            $scope.navigationService.seasonMode();

            //前と次のシーズンのリンクを設定

            var previous:Model.Season = SeasonService.previousSeason($rootScope.season.year, $rootScope.season.seasonType);
            var next:Model.Season = SeasonService.nextSeason($rootScope.season.year, $rootScope.season.seasonType);
            $scope.previousSeason = "?year=" + previous.year + "&seasonType=" + previous.seasonType;
            $scope.nextSeason = "?year=" + next.year + "&seasonType=" + next.seasonType;


            /**
             * アニメリストをタイトルに従ってソートする。
             */
            $scope.sortTitle = ():void => {
                var desc = !!$scope.desc;
                $rootScope.animes = $filter("orderBy")($rootScope.animes, "title", desc);
                $scope.desc = !desc;
            };

            /**
             * アニメリストを視聴状況に従ってソートする。
             */
            $scope.sortViewingHistories = ():void => {
                $rootScope.animes = $filter("viewingSortFilter")($rootScope.animes);
            };

            /**
             * スムーズに画面先頭へスクロールする。
             */
            $scope.resetScroll = ():void => {
                var x = 0;
                var y = 600;
                var duration = 2000; //milliseconds

                //Scroll to the exact position
                scroller.scrollTo(x, y, duration);

                var chunk = 0;
                scroller.scrollDelta(x, chunk, duration);

                var offset = 0;
                scroller.scrollToElement(document.getElementById("index_body"), offset, duration);
            };

            /**
             * 引数で指定したアニメの詳細画面を開く。
             *
             * @param anime 詳細を表示するアニメ
             */
            $scope.open = (anime:any):void => {

                var modalInstance = $modal.open({
                    templateUrl: "/partials/anime_detail.html",
                    controller: "ModalInstanceCtrl",
                    resolve: {
                        anime: function () {
                            return anime;
                        }
                    }
                });

                modalInstance.result.then(() => {
                }, () => {
                });
            };

            /**
             * 視聴状況を登録する。
             *
             * @param anime 視聴状況を登録するアニメ
             * @param mode 視聴状況
             */
            $scope.view = (anime:any, mode:any):void => {
                //パネルのスタイル変更
                anime.status = mode;

                //視聴状況の変更
                $http.post("/api/viewing_histories", {
                    userId: $scope.currentUser.userId,
                    animeId: anime.animeId,
                    status: mode
                }).success(function (data) {
                    console.log(data);
                });
            };
        }
    }
}
