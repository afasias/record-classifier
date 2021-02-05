<html lang="en">
<head>
    <title>Category Explorer</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
    <style>

        body {
            font-family: sans-serif;
        }

        a {
            text-decoration: none;
            color: #00c;
        }

        a:hover {
            text-decoration: underline;
        }

        table {
            margin-top: 30px;
            border-top: #000 solid 1px;
            border-left: #000 solid 1px;
            border-spacing: 0px;
        }

        thead {
            background-color: #ffb;
        }

        td, th {
            text-align: left;
            font-family: sans-serif;
            white-space: nowrap;
            padding: 5px;
            margn: 0px;
            border-bottom: #000 solid 1px;
            border-right: #000 solid 1px;
        }

        td.number {
            text-align: right;
        }

        tbody tr:nth-child(2n+2) {
            background-color: #EEEEEE;
        }

        tbody tr:hover {
            background-color: #DDFFDD;
        }

        tbody tr:nth-child(2n+2):hover {
            background-color: #CDEDCD;
        }

        #chart {
            float: right;
            margin-bottom: 30px;
        }

    </style>
</head>
<body>
<div id="main">
    <span id="path"></span>
    <canvas id="chart" width="400" height="400"></canvas>
    <table id="categories" aria-label="Categories">
        <thead>
        <tr>
            <th scope="col">Category</th>
            <#list aggregations as aggregation>
                <th scope="col">${aggregation}</th>
            </#list>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <table id="records" aria-label="Records">
        <thead>
        <tr>
            <#list fields as field>
                <th scope="col">${field}</th>
            </#list>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<script type=" application/javascript">

  let chart = new Chart('chart', {
    type: 'doughnut',
    data: {
      labels: [],
      datasets: [{
        data: [],
        backgroundColor: [
          '#0000ff',
          '#c500c3',
          '#fa0080',
          '#ff0045',
          '#ff0000',
          '#f66d00',
          '#d7a700',
          '#a0d600',
          '#00ff00',
          '#00e1ad',
          '#00baff',
          '#0087ff',
        ],
        borderColor: [
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
          '#000',
        ],
        borderWidth: 1
      }]
    },
    options: {
      responsive: false,
    }
  });

  let categoryTree = ${categoryTreeJson};

  let fields = ${fieldsJson};

  let aggregations = ${aggregationsJson};

  let defaultAggregation = ${defaultAggregationJson};

  addParents(categoryTree);
  updateView();

  $(window).on('hashchange', function (e) {
    updateView();
  });

  function updateView() {
    const hash = decodeURI(window.location.hash.substr(1));
    if (hash) {
      const category = findCategoryByName(categoryTree, hash);
      displayCategory(category);
    } else {
      displayCategory(categoryTree);
    }
  }

  function findCategoryByName(category, name) {
    if (category.name === name) {
      return category;
    }
    if (category.categories) {
      for (let i = 0; i < category.categories.length; i++) {
        const child = category.categories[i];
        const foundCategory = findCategoryByName(child, name);
        if (foundCategory) {
          return foundCategory;
        }
      }
    }
    return null;
  }

  function addParents(category) {
    $.each(category.categories, function (i) {
      const child = category.categories[i];
      child.parent = category;
      addParents(child);
    })
  }

  function displayCategory(category) {
    updatePath(category);
    updateCategoriesTable(category);
    updateRecordsTable(category);
    updateChart(category);
  }

  function updatePath(category) {
    const path = $('#path');
    path.empty();
    let current = category;
    while (current) {
      const element = current !== category ?
        $('<a>')
          .text(current.name ?? '(undefined)')
          .attr('href', '#' + current.name)
          .data('category', current) :
        $('<span>')
          .text(current.name ?? '(undefined)');
      path.prepend(element).prepend(' &#187; ');
      current = current.parent;
    }
  }

  function updateCategoriesTable(category) {
    const categoriesTable = $('#categories');
    const categories = $('#categories tbody');
    categories.empty();
    categoriesTable.hide();
    if (category.categories) {
      $.each(category.categories, function (i) {
        const child = category.categories[i];
        const ahref = $('<a>')
          .text(child.name ?? '(undefined)')
          .attr('href', '#' + child.name)
          .data('category', child);
        let tr = $('<tr>').append($('<td>').html(ahref));
        $.each(aggregations, function (aggregation) {
          let value = child.aggregations[aggregation];
          const field = aggregations[aggregation].field;
          if (field && fields[field] === 'BigDecimal') {
            value = value.toFixed(2);
          }
          const td = $('<td>').text(value);
          if (field && fields[field] === 'BigDecimal') {
            td.attr('class', 'number');
          }
          tr = tr.append(td);
        });
        categories.append(tr);
      });
      categoriesTable.show();
    }
  }

  function updateRecordsTable(category) {
    const recordsTable = $('#records');
    const records = $('#records tbody');
    records.empty();
    recordsTable.hide();
    if (category.records.length) {
      $.each(category.records, function (i) {
        const record = category.records[i];
        let tr = $('<tr>');
        $.each(fields, function (field) {
          const td = $('<td>').text(record[field]);
          if (fields[field] === 'BigDecimal') {
            td.attr('class', 'number');
          }
          tr = tr.append(td);
        });
        records.append(tr);
      });
      recordsTable.show();
    }
  }

  function updateChart(category) {
    if (category.categories) {
      $('#chart').show();
      chart.data.labels = [];
      chart.data.datasets[0].data = [];
      $.each(category.categories, function (i) {
        const child = category.categories[i];
        chart.data.labels.push(child.name ?? '(undefined)');
        chart.data.datasets[0].data.push(child.aggregations[defaultAggregation]);
      });
      chart.update();
    } else {
      $('#chart').hide();
    }
  }

</script>
</body>
</html>