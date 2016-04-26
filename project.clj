(defproject bitcoin "0.1.0-SNAPSHOT"
  :description "offline paper wallet generator"
  :url "http://diegobasch.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"bitcoinj" "http://distribution.bitcoinj.googlecode.com/git/releases/"}
  :dependencies [[org.bitcoinj/bitcoinj-core "0.13.6"]
                 [org.clojars.dbasch/bip38 "0.1.1"]
                 [org.clojure/tools.cli "0.3.3"]
                 [org.slf4j/slf4j-simple "1.7.21"]
                 [org.clojure/clojure "1.8.0"]
                 [pandect "0.5.4"]]
  :main bitcoin.main)
