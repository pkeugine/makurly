sed -n 1p /Users/pkeugine/Projects/kurly/infra/ip.txt | awk -F'"' '{print $2}'
sed -n 2p /Users/pkeugine/Projects/kurly/infra/ip.txt | awk -F'"' '{print $2}'
app_server_ip=$(echo "$a" | sed -n 2p ../ip.txt | awk -F'"' '{print $2}')
echo "REACT_APP_API_SERVER=http://${app_server_ip}:8080" > /Users/pkeugine/Projects/kurly/infra/production/.env.production
