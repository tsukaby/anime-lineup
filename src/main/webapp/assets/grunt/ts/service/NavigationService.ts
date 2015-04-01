///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module Service {
    "use strict";

    export class NavigationService {
        constructor(public $rootScope:any, public SeasonService:Service.SeasonService) {
        }

        title:string;

        /**
         * title変数に現在のシーズンを設定する。
         */
        seasonMode = ():void => {
            this.title = "シーズン：" + this.$rootScope.season.year + "年 " + this.SeasonService.toJapaneseForSeason(this.$rootScope.season.season);
        };

        /**
         * title変数に検索ワードを設定する。
         *
         * @param searchWord 検索ワード
         */
        searchMode = (searchWord:string):void => {
            this.title = "検索：" + searchWord;
        };
    }
}