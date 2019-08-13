$(function () {
    makeEditable({
            ajaxUrl: "ajax/profile/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "datetime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    // {
                    //     "data": "enabled"
                    // },
                    // {
                    //     "data": "registered"
                    // },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
});