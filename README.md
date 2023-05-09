# Table of Content

<!-- toc-begin -->
* [Overview](#overview)
  * [Measurements](#measurements)
* [Test Cases](#test-cases)
  * [Brainfuck](#brainfuck)
    * [bench.b](#benchb)
    * [mandel.b](#mandelb)
  * [Base64](#base64)
  * [Json](#json)
  * [Matmul](#matmul)
  * [Primes](#primes)
* [Tests Execution](#tests-execution)
  * [Environment](#environment)
  * [Using Docker](#using-docker)
  * [Manual Execution](#manual-execution)
    * [Prerequisites](#prerequisites)
* [Contribution](#contribution)
  * [Makefile guide](#makefile-guide)
    * [Binary executables](#binary-executables)
    * [Compiled artifacts](#compiled-artifacts)
    * [Scripting language](#scripting-language)
  * [README update](#readme-update)
  * [Docker image update](#docker-image-update)
<!-- toc-end -->

# Overview

The benchmarks follow the criteria:

  - They are written as the average software developer would write them, i.e.

    - The algorithms are implemented as cited in public sources;
    - The libraries are used as described in the tutorials, documentation and examples;
    - The used data structures are idiomatic.

  - The used algorithms are similar between the languages (as the reference implementations), variants are acceptable if the reference implementation exists.
  - All final binaries are releases (optimized for performance if possible) as debug performance may vary too much depending on the compiler.

My other benchmarks: [jit-benchmarks](https://github.com/kostya/jit-benchmarks), [crystal-benchmarks-game](https://github.com/kostya/crystal-benchmarks-game)

## Measurements

The measured values are:

 - time spent for the benchmark execution (loading required data and code self-testing are not measured);
 - memory consumption of the benchmark process, reported as `base` + `increase`, where `base` is the RSS before the benchmark and `increase` is the peak increase of the RSS during the benchmark;
 - energy consumption of the CPU package during the benchmark: PP0 (cores) + PP1 (uncores like GPU) + DRAM. Currently, only Intel CPU are supported via the powercap interface.

All values are presented as: `median`.

UPDATE: 2022-12-10

# Test Cases

## Brainfuck

Testing brainfuck implementations using two code samples (bench.b and mandel.b).
Supports two mode:

 - Verbose (default). Prints the output immediately.
 - Quiet (if QUIET environment variable is set). Accumulates the output using Fletcher-16 checksum, and prints it out after the benchmark.

[Brainfuck](brainfuck)

### bench.b

|                 Language |                  Time, s |                                       Memory, MiB |                  Energy, J |
| :----------------------- | -----------------------: | ------------------------------------------------: | -------------------------: |
|  Racket (Syntax Objects) |   1.297 |   110.95 + 0.00 |     47.09 |
|                  C++/g++ |   1.300 |     1.84 + 0.00 |     51.96 |
|                     Rust |   1.545 |     0.94 + 0.00 |     61.73 |
|                     Java |   1.651 |    37.60 + 1.55 |     64.40 |
|                    V/gcc |   1.654 |     1.84 + 0.00 |     64.02 |
|                   D/ldc2 |   1.657 |     3.02 + 0.00 |     66.26 |
|                    D/gdc |   1.661 |     6.59 + 0.00 |     68.09 |
|                    C/gcc |   1.667 |     0.89 + 0.00 |     64.70 |
|                  C/clang |   1.670 |     0.89 + 0.00 |     65.33 |
|               Kotlin/JVM |   1.683 |    41.55 + 0.43 |     65.59 |
|              C++/clang++ |   1.742 |     1.61 + 0.00 |     67.71 |
|                      Zig |   1.835 |     0.91 + 0.00 |     71.17 |
|                       Go |   1.905 |     3.11 + 0.00 |     73.82 |
|                    OCaml |   1.912 |     2.87 + 1.98 |     85.40 |
|              Chez Scheme |   1.933 |    24.84 + 4.34 |     79.82 |
|             F#/.NET Core |   1.952 |   108.79 + 0.60 |     80.12 |
|                  Nim/gcc |   1.962 |     2.00 + 0.00 |     77.19 |
|             C#/.NET Core |   1.979 |   104.35 + 0.21 |     81.29 |
|                   Racket |   2.016 |    92.74 + 0.00 |     81.31 |
|                 Vala/gcc |   2.090 |     4.34 + 0.00 |     78.35 |
|                Nim/clang |   2.111 |     2.29 + 0.00 |     81.86 |
|                 Go/gccgo |   2.176 |    24.22 + 0.00 |     85.32 |
|               Vala/clang |   2.266 |     4.31 + 0.00 |     85.08 |
|                  V/clang |   2.356 |     1.88 + 0.00 |    101.86 |
|                  Crystal |   2.363 |     2.90 + 0.00 |     94.12 |
|                    Julia |   2.581 |   207.32 + 0.65 |     99.89 |
|                    MLton |   2.608 |     0.98 + 0.92 |    104.55 |
|                  C#/Mono |   2.685 |    24.98 + 0.00 |    107.55 |
|                    D/dmd |   3.218 |     3.52 + 0.00 |    123.14 |
|                    Scala |   3.228 |  66.81 + 166.50 |    135.27 |
|         Haskell (MArray) |   3.851 |     4.90 + 5.80 |    154.33 |
|             Haskell (FP) |   3.889 |     5.92 + 4.93 |    165.24 |
|                  Node.js |   4.150 |    42.88 + 0.31 |    165.97 |
|                    Swift |   5.450 |    15.28 + 0.00 |    204.49 |
| Ruby/truffleruby (--jvm) |   6.413 | 409.74 + 674.12 |    309.38 |
|               Lua/luajit |   6.491 |     2.43 + 0.00 |    259.21 |
|         Ruby/truffleruby |   7.043 | 303.07 + 359.89 |    332.99 |
|              Python/pypy |  12.191 |   60.78 + 30.13 |    510.36 |
|                    Idris |  15.762 |    20.63 + 8.82 |    680.89 |
|             Ruby (--jit) |  40.816 |   270.58 + 0.25 |   1635.12 |
|                   Elixir |  43.256 |    71.45 + 0.00 |   1928.27 |
|                      Lua |  50.611 |     2.27 + 0.00 |   1955.56 |
|                     Ruby |  87.260 |    13.96 + 0.00 |   3609.87 |
|               Ruby/jruby |  87.322 | 189.47 + 116.82 |   3849.08 |
|                   Python | 195.534 |    10.41 + 0.00 |   7834.28 |
|                 Tcl (FP) | 271.758 |     3.81 + 0.00 |  11395.20 |
|                     Perl | 315.489 |     7.14 + 0.00 |  12703.58 |
|                Tcl (OOP) | 524.988 |     3.96 + 0.00 | 22223.01 |

### mandel.b

[Mandel in Brainfuck](brainfuck/mandel.b)

|                 Language |                 Time, s |                                       Memory, MiB |                Energy, J |
| :----------------------- | ----------------------: | ------------------------------------------------: | -----------------------: |
|                  C++/g++ | 10.145 |     1.83 + 2.39 |  410.27 |
|                    C/gcc | 14.201 |     0.89 + 0.81 |  557.98 |
|  Racket (Syntax Objects) | 14.285 |  112.31 + 71.15 |  566.11 |
|               Kotlin/JVM | 14.315 |    41.63 + 1.19 |  586.70 |
|                   D/ldc2 | 14.920 |     3.04 + 0.84 |  598.76 |
|                      Zig | 15.007 |     0.91 + 1.41 |  592.52 |
|                  C/clang | 15.128 |     0.89 + 0.87 |  647.86 |
|                     Rust | 15.262 |     0.91 + 1.10 |  598.93 |
|                    D/gdc | 15.371 |     6.57 + 1.36 |  644.91 |
|              C++/clang++ | 15.573 |     1.62 + 1.98 |  636.74 |
|                    V/gcc | 15.903 |     1.85 + 1.19 |  632.63 |
|                  Crystal | 15.924 |     2.91 + 0.60 |  663.38 |
|             C#/.NET Core | 16.346 |   104.49 + 0.88 |  682.67 |
|                    Swift | 18.069 |    16.12 + 0.00 |  727.04 |
|                 Vala/gcc | 18.476 |     4.22 + 1.45 |  691.94 |
|                     Java | 18.659 |    37.73 + 2.05 |  744.47 |
|                       Go | 19.488 |     3.07 + 1.28 |  730.68 |
|                 Go/gccgo | 19.922 |    24.25 + 1.27 |  819.93 |
|               Vala/clang | 20.250 |     4.26 + 1.46 |  783.48 |
|                    Scala | 20.975 |  67.25 + 141.43 |  881.11 |
|                  Nim/gcc | 22.399 |     2.00 + 0.52 |  901.24 |
|                  V/clang | 23.011 |     1.87 + 1.18 |  977.86 |
|                Nim/clang | 24.833 |     2.28 + 0.51 |  987.29 |
|                    OCaml | 26.219 |     3.45 + 3.74 | 1247.55 |
|              Chez Scheme | 27.790 |    25.31 + 3.92 | 1215.67 |
|                  Node.js | 28.194 |    42.91 + 6.17 | 1178.23 |
|                    Julia | 29.584 |   207.95 + 0.49 | 1126.40 |
|                  C#/Mono | 31.521 |    25.01 + 0.82 | 1337.07 |
|             F#/.NET Core | 34.950 |   108.86 + 2.22 | 1442.68 |
|               Lua/luajit | 35.086 |     2.44 + 0.44 | 1401.10 |
|                   Racket | 36.847 |    93.33 + 0.25 | 1621.75 |
|         Haskell (MArray) | 37.327 |     5.72 + 6.11 | 1520.44 |
|                    D/dmd | 37.414 |     3.50 + 0.97 | 1365.92 |
|                    MLton | 44.858 |     1.61 + 4.11 | 1924.75 |
|              Python/pypy | 48.295 |   60.86 + 30.54 | 2043.82 |
| Ruby/truffleruby (--jvm) | 66.508 | 408.76 + 611.12 | 2784.36 |
|                    Idris | 67.460 |    21.96 + 9.56 | 2915.79 |
|             Haskell (FP) | 79.443 |    5.82 + 75.83 | 3315.86 |
|         Ruby/truffleruby | 81.328 | 302.54 + 263.13 | 3816.09 |


## Base64

Testing base64 encoding/decoding of the large blob into the newly allocated buffers.

[Base64](base64)

|                  Language |                 Time, s |                                       Memory, MiB |               Energy, J |
| :------------------------ | ----------------------: | ------------------------------------------------: | ----------------------: |
|            C/gcc (aklomp) |  0.099 |     2.07 + 0.00 |   4.64 |
|          C/clang (aklomp) |  0.099 |     2.07 + 0.00 |   4.59 |
|                       PHP |  0.106 |    17.31 + 0.00 |   4.78 |
|                      Rust |  0.970 |     2.08 + 0.21 |  39.37 |
|                   C/clang |  0.997 |     2.00 + 0.00 |  36.74 |
|                     C/gcc |  1.012 |     2.07 + 0.00 |  37.18 |
|                    D/ldc2 |  1.037 |     3.40 + 3.61 |  43.16 |
|                   Nim/gcc |  1.082 |     1.68 + 5.23 |  42.43 |
|                 Nim/clang |  1.095 |     1.90 + 5.27 |  43.55 |
|                   Crystal |  1.102 |     3.49 + 1.18 |  44.99 |
|                     V/gcc |  1.349 |  2.39 + 2376.18 |  50.68 |
|                   V/clang |  1.380 |  2.38 + 2376.79 |  52.53 |
|              Ruby (--jit) |  1.386 |  271.00 + 90.38 |  54.11 |
|                      Ruby |  1.388 |   14.65 + 58.72 |  53.68 |
|                      Java |  1.511 |  39.32 + 254.12 |  62.27 |
|                     Scala |  1.606 |  64.90 + 330.26 |  68.84 |
|                Kotlin/JVM |  1.636 |  42.55 + 249.05 |  69.13 |
|                  Vala/gcc |  1.643 |     5.54 + 0.13 |  62.89 |
|                Vala/clang |  1.644 |     5.50 + 0.15 |  63.06 |
|   C++/clang++ (libcrypto) |  1.711 |     5.09 + 0.71 |  68.44 |
|       C++/g++ (libcrypto) |  1.712 |     5.70 + 0.72 |  68.35 |
|                   Node.js |  1.724 |   42.61 + 43.70 |  69.32 |
|                        Go |  1.877 |     4.25 + 4.01 |  80.54 |
|       Perl (MIME::Base64) |  1.951 |    14.90 + 0.13 |  78.31 |
|              F#/.NET Core |  2.342 |  109.31 + 46.62 |  87.36 |
|                     D/gdc |  2.436 |     7.55 + 3.35 | 106.92 |
|              C#/.NET Core |  2.647 |  104.54 + 47.83 |  96.55 |
|                    Python |  2.711 |    10.28 + 0.60 | 105.56 |
|                       Zig |  2.951 |     1.58 + 0.00 | 130.14 |
|                     D/dmd |  3.026 |     4.15 + 3.35 | 129.20 |
|               Python/pypy |  3.582 |   60.86 + 31.50 | 157.11 |
|                       Tcl |  3.632 |     5.10 + 0.00 | 146.59 |
|                  Go/gccgo |  3.714 |    25.25 + 7.84 | 166.93 |
|                   C#/Mono |  4.712 |   25.65 + 18.70 | 194.51 |
|                     Julia |  5.051 |  226.05 + 42.61 | 189.58 |
|  Ruby/truffleruby (--jvm) |  5.136 | 407.71 + 211.28 | 240.17 |
|                Ruby/jruby | 10.708 |  186.69 + 79.64 | 421.28 |
| Perl (MIME::Base64::Perl) | 13.619 |    16.21 + 0.23 | 572.56 |
|          Ruby/truffleruby | 15.536 | 302.86 + 263.68 | 649.08 |

## Json

Testing parsing and simple calculating of values from a big JSON file.

Few notes:

 - gason mutates input strings;
 - simdjson requires input strings with batch of trailing zeros: a special zero padding for SIMD instructions;
 - DAW JSON Link "NoCheck" skips some JSON structure correctness checks;
 - DAW JSON Link, gason, default (not "Precise") RapidJSON, and D implementations except Mir-based
have some inaccuracies in number parsing:
   - [DAW JSON Link's number parsing issue](https://github.com/beached/daw_json_link/issues/226)
   - [gason's number parsing issue](https://github.com/vivkin/gason/issues/35)
   - [D stdlib number parsing issue](https://issues.dlang.org/show_bug.cgi?id=20967)

[Json](json)

|                            Language |                 Time, s |                                        Memory, MiB |                Energy, J |
| :---------------------------------- | ----------------------: | -------------------------------------------------: | -----------------------: |
|    C++/clang++ (simdjson On-Demand) |  0.066 |   112.52 + 60.11 |    2.71 |
|        C++/g++ (simdjson On-Demand) |  0.066 |   113.29 + 60.11 |    2.72 |
| C++/clang++ (DAW JSON Link NoCheck) |  0.070 |    112.38 + 0.00 |    2.95 |
|     C++/g++ (DAW JSON Link NoCheck) |  0.085 |    113.23 + 0.00 |    3.36 |
|         C++/clang++ (DAW JSON Link) |  0.086 |    112.41 + 0.00 |    3.61 |
|             C++/g++ (DAW JSON Link) |  0.101 |    113.23 + 0.00 |    4.14 |
|                     C++/g++ (gason) |  0.133 |   113.15 + 96.96 |    5.26 |
|              C++/g++ (simdjson DOM) |  0.133 |  113.27 + 176.90 |    5.70 |
|                 C++/clang++ (gason) |  0.144 |   112.35 + 96.97 |    5.82 |
|          C++/clang++ (simdjson DOM) |  0.144 |  112.47 + 177.15 |    6.15 |
|                 C++/g++ (RapidJSON) |  0.168 |  113.23 + 122.23 |    7.01 |
|             C++/clang++ (RapidJSON) |  0.230 |  112.41 + 128.97 |    9.57 |
|                          Go (Sonic) |  0.237 |  119.77 + 111.84 |   10.21 |
|         C++/g++ (RapidJSON Precise) |  0.243 |  113.23 + 128.88 |   10.27 |
|                  Go (goccy/go-json) |  0.271 |  115.83 + 112.15 |   10.63 |
|     C++/clang++ (RapidJSON Precise) |  0.313 |  112.41 + 129.00 |   13.43 |
|                C++/g++ (Boost.JSON) |  0.401 |  113.30 + 435.95 |   16.68 |
|             C++/g++ (RapidJSON SAX) |  0.404 |    113.02 + 0.00 |   16.62 |
|            C++/clang++ (Boost.JSON) |  0.421 |  112.42 + 436.31 |   17.73 |
|     C++/g++ (RapidJSON SAX Precise) |  0.451 |    113.02 + 0.00 |   19.37 |
|                       Go (jsoniter) |  0.517 |    230.90 + 1.35 |   21.68 |
|                             Node.js |  0.552 |  152.51 + 187.99 |   25.65 |
|         C++/clang++ (RapidJSON SAX) |  0.600 |    194.64 + 0.00 |   23.95 |
|                     Java (DSL-JSON) |  0.603 |  259.68 + 190.97 |   30.71 |
|                         Python/pypy |  0.688 |  280.92 + 125.74 |   29.52 |
| C++/clang++ (RapidJSON SAX Precise) |  0.736 |    194.60 + 0.00 |   30.11 |
|                                  Go |  0.856 |   117.05 + 79.91 |   34.96 |
|                    C++/g++ (json-c) |  1.176 | 113.39 + 1216.08 |   48.91 |
|                C++/clang++ (json-c) |  1.181 | 112.68 + 1216.08 |   49.16 |
|                            Go/gccgo |  1.269 |   138.83 + 83.59 |   53.56 |
|              C++/clang++ (Nlohmann) |  1.272 |  112.52 + 360.17 |   54.07 |
|                 CPython (UltraJSON) |  1.444 |  122.17 + 544.51 |   53.21 |
|                  C++/g++ (Nlohmann) |  1.507 |  113.38 + 448.03 |   61.19 |
|                              Python |  1.525 |  120.25 + 375.17 |   59.36 |
|    C++/clang++ (Boost.PropertyTree) |  3.166 | 194.88 + 1232.84 |  130.92 |
|        C++/g++ (Boost.PropertyTree) |  3.336 | 113.22 + 1440.12 |  139.30 |


## Matmul

Testing allocating and multiplying matrices.

[Matmul](matmul)

|                 Language |                   Time, s |                                       Memory, MiB |                  Energy, J |
| :----------------------- | ------------------------: | ------------------------------------------------: | -------------------------: |
|          D/ldc2 (lubeck) |    0.043 |    6.06 + 57.94 |      4.41 |
|      V/gcc (VSL + CBLAS) |    0.047 |    6.96 + 58.03 |      4.55 |
|    V/clang (VSL + CBLAS) |    0.047 |    7.03 + 57.93 |      4.57 |
|    Nim/gcc (Arraymancer) |    0.066 |    4.91 + 57.94 |      5.47 |
|           Python (NumPy) |    0.067 |   30.07 + 58.18 |      6.27 |
|  Nim/clang (Arraymancer) |    0.069 |    6.24 + 57.58 |      6.02 |
|          C++/g++ (Eigen) |    0.072 |   31.90 + 58.05 |      5.39 |
|      C++/clang++ (Eigen) |    0.072 |   36.08 + 54.22 |      5.90 |
|              Java (ND4J) |    0.079 |  106.62 + 91.92 |      6.18 |
|           Rust (ndarray) |    0.089 |    2.39 + 68.47 |      6.05 |
|       Julia (threads: 2) |    0.137 |  237.36 + 52.34 |      7.19 |
|       Julia (threads: 1) |    0.187 |  237.13 + 52.82 |      8.46 |
|            V/clang (VSL) |    0.242 |    7.04 + 51.83 |     17.31 |
|              V/gcc (VSL) |    0.477 |    7.06 + 51.82 |     32.25 |
|          Julia (no BLAS) |    1.052 |  224.75 + 51.52 |     46.61 |
|                   D/ldc2 |    1.718 |    3.27 + 70.46 |     63.13 |
|                    D/gdc |    1.870 |    6.83 + 70.88 |     73.06 |
|                    D/dmd |    1.874 |    3.58 + 70.48 |     71.02 |
|                    C/gcc |    3.029 |    1.47 + 68.70 |    109.60 |
|                    V/gcc |    3.038 |    2.13 + 69.01 |    112.35 |
|                  V/clang |    3.060 |    2.43 + 68.95 |    104.62 |
|               Vala/clang |    3.061 |    3.93 + 69.80 |    104.74 |
|                  C/clang |    3.062 |    1.46 + 68.73 |    104.49 |
|                     Rust |    3.065 |    2.11 + 68.57 |    105.21 |
|                      Zig |    3.074 |    1.50 + 68.89 |    108.77 |
|                  Nim/gcc |    3.090 |    2.51 + 66.26 |    111.06 |
|                     Java |    3.093 |   39.19 + 68.43 |    109.77 |
|                    Swift |    3.095 |    7.30 + 68.92 |    109.29 |
|                Nim/clang |    3.122 |    2.82 + 66.00 |    106.98 |
|                 Vala/gcc |    3.126 |    3.89 + 69.86 |    114.22 |
|                       Go |    3.153 |    3.85 + 73.12 |    113.47 |
|                 Go/gccgo |    3.160 |   24.62 + 73.50 |    112.00 |
|                  Crystal |    3.161 |    3.44 + 59.97 |    115.46 |
|                  Node.js |    3.238 |   47.55 + 76.79 |    130.43 |
|              Python/pypy |    3.274 |   61.70 + 68.69 |    136.27 |
|                    Scala |    3.325 |  65.60 + 140.85 |    121.60 |
|               Kotlin/JVM |    3.664 |   40.44 + 68.06 |    152.07 |
|             C#/.NET Core |    4.384 |  106.09 + 69.06 |    175.92 |
|                  C#/Mono |    7.414 |   25.41 + 69.60 |    299.70 |
|         Ruby/truffleruby |   17.828 | 380.74 + 518.10 |    660.11 |
| Ruby/truffleruby (--jvm) |   28.869 | 552.53 + 367.72 |    994.44 |
|                     Ruby |  198.042 |   14.98 + 69.05 |   8578.84 |
|             Ruby (--jit) |  198.618 |  271.64 + 72.66 |   8637.61 |
|                     Perl |  225.366 |   9.57 + 599.64 |   9143.92 |
|                   Python |  278.785 |   10.71 + 68.87 |  11177.48 |
|                      Tcl |  335.868 |   7.32 + 400.18 | 14014.34 |
|               Ruby/jruby | 399.505 | 268.81 + 756.38 | 16344.90 |

## Primes

Testing:

 - generating primes using the optimized [sieve of Atkin](https://www.geeksforgeeks.org/sieve-of-atkin/);
 - prefix search for their decimal numbers using Trie data structure.

Notes:

 - All languages but V and Python use unordered hashmaps (V and Python don't provide those out of box, and
 their hashmaps use keys in the insertion order);
 - The results are always sorted (could be unstable or stable though).

[Primes](primes)

|                 Language |                Time, s |                                       Memory, MiB |               Energy, J |
| :----------------------- | ---------------------: | ------------------------------------------------: | ----------------------: |
|                  C++/g++ | 0.071 |    3.68 + 77.91 |   2.71 |
|                      Zig | 0.072 |    0.91 + 57.81 |   2.97 |
|              C++/clang++ | 0.072 |    1.65 + 61.74 |   2.70 |
|                  V/clang | 0.106 |   1.91 + 212.02 |   4.25 |
|                    V/gcc | 0.107 |   1.87 + 208.57 |   4.35 |
|                     Rust | 0.115 |    0.94 + 74.00 |   4.47 |
|                  Crystal | 0.151 |    2.88 + 89.06 |   6.01 |
|                     Java | 0.164 |  38.52 + 153.60 |   9.25 |
|                  Node.js | 0.225 |  42.34 + 150.08 |  11.26 |
|                Nim/clang | 0.298 |   1.99 + 598.51 |  11.77 |
|                    Scala | 0.299 |  67.35 + 148.34 |  15.31 |
|                  Nim/gcc | 0.324 |   1.69 + 599.54 |  12.39 |
|               Lua/luajit | 0.338 |   1.21 + 157.08 |  13.26 |
|                    Julia | 0.593 | 226.44 + 376.93 |  23.15 |
|              Python/pypy | 0.830 |  59.75 + 251.87 |  32.55 |
| Ruby/truffleruby (--jvm) | 1.221 | 409.10 + 542.80 |  79.93 |
|             Ruby (--jit) | 1.442 | 270.68 + 146.96 |  58.58 |
|                      Lua | 1.493 |   2.28 + 284.23 |  58.17 |
|         Ruby/truffleruby | 1.657 | 299.73 + 496.36 |  92.44 |
|                     Ruby | 2.063 |  13.96 + 146.97 |  83.40 |
|               Ruby/jruby | 2.143 | 187.79 + 545.57 | 111.13 |
|                   Python | 4.984 |  10.42 + 234.84 | 194.67 |

# Tests Execution

## Environment

CPU: Intel(R) Xeon(R) E-2324G

Base Docker image: Debian GNU/Linux bookworm/sid

| Language         | Version                         |
| ---------------- | ------------------------------- |
| .NET Core        | 7.0.100                         |
| C#/.NET Core     | 4.4.0-4.22520.11 (9e075f03)     |
| C#/Mono          | 6.8.0.105                       |
| Chez Scheme      | 9.5.8                           |
| Clojure          | "1.11.1"                        |
| Crystal          | 1.6.2                           |
| D/dmd            | v2.101.0                        |
| D/gdc            | 12.2.0                          |
| D/ldc2           | 1.30.0                          |
| Elixir           | 1.14.0                          |
| F#/.NET Core     | 12.4.0.0 for F# 7.0             |
| Go               | go1.19.4                        |
| Go/gccgo         | 12.2.0                          |
| Haskell          | 9.4.3                           |
| Idris 2          | 0.6.0                           |
| Java             | 19.0.1                          |
| Julia            | v"1.8.3"                        |
| Kotlin           | 1.7.21                          |
| Lua              | 5.4.4                           |
| Lua/luajit       | 2.1.0-beta3                     |
| MLton            | 20210117                        |
| Nim              | 1.6.10                          |
| Node.js          | v19.2.0                         |
| OCaml            | 4.14.0                          |
| PHP              | 8.1.12                          |
| Perl             | v5.36.0                         |
| Python           | 3.10.8                          |
| Python/pypy      | 7.3.10-final0 for Python 3.9.15 |
| Racket           | "8.7"                           |
| Ruby             | 3.1.3p185                       |
| Ruby/jruby       | 9.4.0.0                         |
| Ruby/truffleruby | 22.3.0                          |
| Rust             | 1.65.0                          |
| Scala            | 3.2.1                           |
| Swift            | 5.7.1                           |
| Tcl              | 8.6                             |
| V                | 0.3.2 d62fc77                   |
| Vala             | 0.56.3                          |
| Zig              | 0.10.0                          |
| clang/clang++    | 14.0.6                          |
| gcc/g++          | 12.2.0                          |

## Using Docker

Build the image:

    $ docker build docker/ -t benchmarks

The pinned packages could be missing, in that case please update them with:

    $ ./run.sh update_apt

Run the image:

    $ docker run -it --rm -v $(pwd):/src benchmarks <cmd>

where `<cmd>` is:

 - `versions` (print installed language versions);
 - `shell` (start the shell);
 - `brainfuck bench` (build and run Brainfuck bench.b benchmarks);
 - `brainfuck mandel` (build and run Brainfuck mandel.b benchmarks);
 - `base64` (build and run Base64 benchmarks);
 - `json` (build and run Json benchmarks);
 - `matmul` (build and run Matmul benchmarks);
 - `primes` (build and run Primes benchmarks);

Please note that the actual measurements provided in the project are taken semi-manually (via `shell`) as the full update takes days and could have occassional issues in Docker.

There is a `./run.sh` that could be used to simplify Docker usage:

 - `./run.sh build` (build the image);
 - `./run.sh make versions` (run the image with the `versions` command);
 - `sudo ./run.sh shell` (run the image with the `shell' command, sudo is required to read energy levels).

## Manual Execution

Makefiles contain recipes for building and executing tests with the
proper dependencies. Please use `make run` (and `make run2` where applicable).
The measurements are taken using `analyze.rb` script:

    $ cd <test suite>
    $ ../analyze.rb make run
    $ ../analyze.rb make run[<single test>]

Please note that the measurements could take hours. It uses 10 iterations
by default, but it could be changed using ATTEMPTS environment variable:

    $ ATTEMPTS=1 ../analyze.rb make run

### Prerequisites

Please use [Dockerfile](docker/Dockerfile) as a reference regarding which
packages and tools are required.

For all (optional):

 - [Powercap](https://github.com/powercap/powercap) for reading energy
counters in Linux (Debian package `powercap-utils`).

For Python:

 - [NumPy](https://numpy.org/) for matmul tests
(Debian package `python3-numpy`).
 - [UltraJSON](https://pypi.org/project/ujson/) for JSON tests
(Debian package `python3-ujson`).


For C++:

 - [Boost](https://www.boost.org/) for JSON tests
(Debian package `libboost-dev`).
 - [JSON-C](https://github.com/json-c/json-c) for JSON tests
(Debian package `libjson-c-dev`).

For Rust:

 - [libjq](https://stedolan.github.io/jq/) for jq test
(Debian packages `libjq-dev`, `libonig-dev` and environment variable
`JQ_LIB_DIR=/usr/lib/x86_64-linux-gnu/`).

For Java, Scala:

 - [Coursier](https://get-coursier.io/) for downloading Maven artifacts.

For Haskell:

 - [network](http://hackage.haskell.org/package/network) for
TCP connectivity between the tests and the test runner.
 - [raw-strings-qq](http://hackage.haskell.org/package/raw-strings-qq) for
raw string literals used in tests.

For Perl:

 - [cpanminus](https://metacpan.org/pod/App::cpanminus) for installing
modules from CPAN (Debian package `cpanminus`).

For Vala:

 - [JSON-GLib](https://wiki.gnome.org/Projects/JsonGlib) for JSON tests
 (Debian package `libjson-glib-dev`).

# Contribution

Please follow the criteria specified in the [overview](#overview). Besides
that please ensure that the communication protocol between a test and the
test runner is satisfied:

 - The test runner listens on localhost:9001;
 - All messages are sent using TCP sockets closed immediately after the
message has been sent;
 - There are two messages sent from a test (it establishes the measurement
boundary):
    1. The beginning message having the format *name of the test*/t*process ID*
(the process ID is used to measure the memory consumption). Please note that
the name of the test couldn't use Tab character as it's a delimiter;
    2. The end message with any content (mostly it's "stop" for consistency).
 - The test runner could be unavailable (if the test is launched as is) and
the test should gracefully handle it.

## Makefile guide

### Binary executables

If the test is compiled into a single binary, then two sections of
the `Makefile` require changes:

 - append a new target (the final binary location) into `executables`
variable;
 - append the proper target rule.

### Compiled artifacts

If the test is compiled, but can't be executed directly as a binary, then
three sections of the `Makefile` require changes:

 - append a new target (the final artifact location) into `artifacts`
variable;
 - append the proper target rule to compile the test;
 - append `run[<target_artifact>]` rule to run the test.

### Scripting language

If the test doesn't require compilation, then two sections of the `Makefile`
requires changes:

 - append `run[<script_file>]` into `all_runners` variable;
 - append `run[<script_file>]` rule to run the test.

## README update

TOC is regenerated using [git-markdown-toc](https://github.com/ildar-shaimordanov/git-markdown-toc):

```
./run.sh toc
```

## Docker image update

Debian packages are pinned and updated with the script
(first, please ensure that the image is fine with the linter):

```
./run.sh lint
./run.sh update_apt
```
