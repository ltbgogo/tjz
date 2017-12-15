
$(function() {
    /**
     * 分页组件
     */
    Vue.component('v-page', {
        props : ['href', 'totalPages'],
        template : `
            <nav>
                <ul class="pagination">
                    <li>
                        <a v-bind:href="href + 'page=0'">
                            <span>&laquo;</span>
                        </a>
                    </li>
                    <li v-for="n in generatePages(totalPages)" v-bind:class="{active: n == currentPage}">
                        <a v-bind:href="href + 'page=' + n">{{n + 1}}</a>
                    </li>
                    <li>
                        <a v-bind:href="href + 'page=' + (totalPages - 1)">
                            <span>&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        `,
        data: function() {
            return {
                currentPage: $common.getUrlParam("page", 0)
            };
        },
        methods: {
            generatePages: function (totalPages) {
                var startPageIndex = -1;
                var endPageIndex = -1;
                var MAX_BUTTONS = 6;
                if (totalPages > MAX_BUTTONS) {
                    if (this.currentPage > MAX_BUTTONS / 2) {
                        startPageIndex += this.currentPage - MAX_BUTTONS / 2;
                        endPageIndex = startPageIndex + MAX_BUTTONS - 1;
                        if (endPageIndex + 1 > totalPages) {
                            startPageIndex = startPageIndex - (endPageIndex + 1 - totalPages);
                            endPageIndex = startPageIndex + MAX_BUTTONS - 1;
                        }
                    } else {
                        startPageIndex = 0;
                        endPageIndex = MAX_BUTTONS - 1;
                    }
                }  else {
                    startPageIndex = 0;
                    endPageIndex = totalPages - 1;
                }
                var pages = [];
                for (var i = startPageIndex; i <= endPageIndex; i++) {
                    pages.push(i);
                }
                return pages;
            }
        }
    });
});