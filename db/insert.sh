#!/bin/bash

function insert() {
  DATABASE=$1
  PGPASSWORD=postgres psql -U postgres -h localhost -d $DATABASE < db/insert.sql
}

insert "trailblazers"
