function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "ajax/profile/meals/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("ajax/profile/meals/", updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: "ajax/profile/meals/",
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": "ajax/profile/meals/",
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return data.substring(0, 10) + " " + data.substring(11, 16);
                        }
                        return data;
                    }
                },
                {
                    "data": "description",
                    "render": function (data, type, row) {
                        if (type === "display" && row.excess !== undefined) {
                            if (row.excess) {
                                return "<p style='color: blue'>" + data + "</p>";
                            } else {
                                return "<p style='color: green'>" + data + "</p>";
                            }
                        }
                        return data;
                    }
                },
                {
                    "data": "calories"
                },
                {
                    // "defaultContent": "Edit",
                    // "orderable": false
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    // "defaultContent": "Delete",
                    // "orderable": false
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled) {
                    $(row).attr("data-mealExcess", data.excess);
                }
            }
        }),
        updateTable: updateFilteredTable
    });
});