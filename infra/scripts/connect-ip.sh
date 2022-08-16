sed -n 1p /Users/pkeugine/Projects/kurly/infra/production/ip.txt | awk -F'"' '{print $2}'
sed -n 2p /Users/pkeugine/Projects/kurly/infra/production/ip.txt | awk -F'"' '{print $2}'
app_server_ip=$(echo "$a" | sed -n 3p /Users/pkeugine/Projects/kurly/infra/information.txt | rev | cut -d= -f1 | rev)
echo "REACT_APP_API_SERVER=http://${app_server_ip}:8080" > /Users/pkeugine/Projects/kurly/infra/production/.env.production
