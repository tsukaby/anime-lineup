///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module Service {
    "use strict";

    export class AnimeSearchService {
        constructor(public $http:ng.IHttpService, public $rootScope:any) {
        }

        /**
         * タイトルによるアニメ検索。
         * @param title 検索するアニメのタイトル
         */
        searchByTitle = (title:string):void => {
            if (!title) {
                return;
            }

            //すべてのアニメの一覧からタイトルで検索

            var that:any = this;
            if (!this.$rootScope.currentUser) {
                this.$http.get("/api/animes?name=" + title, {cache: true}).success((data:Array<Model.Anime>) => {
                    that.$rootScope.animes = data;
                });
            } else {
                // TODO for login user
                this.$http.get("/api/members/animes?name=" + title, {cache: true}).success((data:Array<Model.Anime>) => {
                    that.$rootScope.animes = data;
                });
            }
        };

        /**
         * シーズンによるアニメ検索。
         *
         * @param year 検索するアニメの放送年
         * @param seasonType 検索するアニメの季節
         * @param callback 検索完了後のコールバック関数
         */
        searchBySeason = (year:number, seasonType:number, callback?:() => void):void => {
            if (!year || !seasonType) {
                return;
            }

            //すべてのアニメからシーズンで検索
            var that = this;
            if (!this.$rootScope.currentUser) {
                this.$http.get("/api/animes?year=" + year + "&season_type=" + seasonType, {cache: true}).success((data:Array<Model.Anime>) => {
                    that.$rootScope.animes = data;
                    if (angular.isDefined(callback)) {
                        callback();
                    }
                });
            } else {
                // TODO for login user
                this.$http.get("/api/animes?year=" + year + "&season_type=" + seasonType, {cache: true}).success((data:Array<Model.Anime>) => {
                    that.$rootScope.animes = data;
                    if (angular.isDefined(callback)) {
                        callback();
                    }
                });
            }
        };

        /**
         * 特に条件を指定しないアニメ検索。現在の日時を基準としたシーズン検索を実行する。
         *
         * @param callback 検索完了後のコールバック関数
         */
        searchByDefault = (callback?:() => void) => {
            var year:number = this.$rootScope.season.year;
            var seasonType:number = this.$rootScope.season.seasonType;

            this.searchBySeason(year, seasonType, callback);
        }
    }
}