///<reference path="../typings/angularjs/angular.d.ts" />

///<reference path="../Model.ts" />

module Service {
    "use strict";

    export class SeasonService {
        constructor(public $http:ng.IHttpService) {
            $http.get("/api/seasons", {cache: true}).success((data:Array<Model.Season>) => {
                this.seasons = data;
            });
        }

        seasons:Array<Model.Season>;

        /**
         * 現在日時から現在のシーズンを取得する
         *
         * @returns {Model.Season} 現在のシーズン
         */
        currentSeason():Model.Season {
            var now = new Date();
            var year = now.getFullYear();
            var season;
            if (0 <= now.getMonth() && now.getMonth() <= 2) {
                season = 1;
            } else if (3 <= now.getMonth() && now.getMonth() <= 5) {
                season = 2;
            } else if (6 <= now.getMonth() && now.getMonth() <= 8) {
                season = 3;
            } else if (9 <= now.getMonth() && now.getMonth() <= 11) {
                season = 4;
            }

            return new Model.Season(year, season);
        }

        /**
         * 引数で指定したシーズンの次のシーズンを取得する。
         *
         * @param year シーズンの年
         * @param seasonType シーズンの季節
         * @returns {*} 次のシーズン
         */
        nextSeason(year:number, seasonType:number):Model.Season {
            if (!year || isNaN(year)) {
                // 数値でない場合
                return undefined;
            }

            var tmpYear:number;
            if (seasonType === 4) {
                // アニメは冬シーズンから始まるため、秋の次は年度が増える
                tmpYear = year + 1;
            } else {
                tmpYear = year;
            }

            var tmpSeason:number;
            switch (seasonType) {
                case 1:
                    tmpSeason = 2;
                    break;
                case 2:
                    tmpSeason = 3;
                    break;
                case 3:
                    tmpSeason = 4;
                    break;
                case 4:
                    tmpSeason = 1;
                    break;
                default:
                    tmpSeason = 0;
                    break;
            }

            return new Model.Season(tmpYear, tmpSeason);
        }

        /**
         * 引数で指定したシーズンの前のシーズンを取得する。
         *
         * @param year シーズンの年
         * @param seasonType シーズンの季節
         * @returns {*} 前のシーズン
         */
        previousSeason(year:number, seasonType:number):Model.Season {
            if (!year || isNaN(year)) {
                // 数値でない場合
                return undefined;
            }

            var tmpYear:number;
            if (seasonType === 1) {
                // アニメは冬シーズンから始まるため、冬の前は年度が減る
                tmpYear = year - 1;
            } else {
                tmpYear = year;
            }

            var tmpSeason:number;
            switch (seasonType) {
                case 1:
                    tmpSeason = 4;
                    break;
                case 2:
                    tmpSeason = 1;
                    break;
                case 3:
                    tmpSeason = 2;
                    break;
                case 4:
                    tmpSeason = 3;
                    break;
                default:
                    tmpSeason = 0;
                    break;
            }

            return new Model.Season(tmpYear, tmpSeason);
        }

        /**
         * シーズンを日本語に変換した文字列を取得する。
         *
         * @param seasonType シーズン
         * @returns {*} シーズンの日本語名
         */
        toJapaneseForSeason(seasonType:number):string {
            var seasons = ["", "冬", "春", "夏", "秋"];
            return seasons[seasonType];
        }

        /**
         * データが存在し、選択可能なシーズンの一覧を取得する。
         *
         * @returns {Array} シーズンの一覧
         */
        getSeasons():Array<Model.Season> {
            return this.seasons;
        }

        /**
         * 引数で指定したシーズンが、選択可能なシーズンであるかどうかを確認する。
         *
         * @param season 調べるシーズン
         * @returns {boolean} trueの場合、選択可能である
         */
        hasSeasons(season:Model.Season):boolean {
            for (var i = 0; i < this.seasons.length; i++) {
                if (this.seasons[i].year === season.year && this.seasons[i].seasonType === season.seasonType) {
                    return true;
                }
            }

            return false;
        }

    }
}