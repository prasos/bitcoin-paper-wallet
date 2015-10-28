(ns bitcoin.miniwallet
  (:gen-class :main true)
  (:import java.math.BigInteger
           [org.bitcoinj.core Address ECKey NetworkParameters DumpedPrivateKey])
  (:require [clojure.string :as str]
            [pandect.algo.sha256 :refer :all]))

(defn private-to-address [k]
  (.toString (Address. (NetworkParameters/prodNet) (.getPubKeyHash k))))

(defn private-to-pubkey-string [k]
  (format "%0130x" (java.math.BigInteger. (.getPubKey k))))

(defn gen-key []
  ;; hack to get the key uncompressed, because bitcoinj makes it compressed by default.
  (.getPrivateKeyEncoded (ECKey. (.getPrivKeyBytes (ECKey.)) nil) (NetworkParameters/prodNet)))

(defn minikey-candidate []
  (let [key-str (.toString (gen-key))]
    (str "S" (subs key-str 2 31))))

(defn minikey-validate [key-str]
  (.startsWith (sha256 (str key-str "?")) "00"))

(defn gen-minikey []
  (loop [testkey (minikey-candidate)]
    (if (minikey-validate testkey)
         testkey
      (recur (minikey-candidate)))))

(defn minikey-to-private [minikey]
  (ECKey/fromPrivate (BigInteger. (sha256 minikey) 16) false))

(defn gen-miniwallet []
  (let [key-str (gen-minikey)
        key (minikey-to-private key-str)]
    (println key-str (private-to-pubkey-string key) (private-to-address key))))

(defn -main [& args]
  (doseq [x (range (bigint (first args)))] (gen-miniwallet)))
